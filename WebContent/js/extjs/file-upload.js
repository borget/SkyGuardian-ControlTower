Ext.require([
    'Ext.form.field.File',
    'Ext.form.field.Number',
    'Ext.form.Panel',
    'Ext.window.MessageBox'
]);

Ext.onReady(function() {

//  Class which shows invisible file input field.
    if (window.location.href.indexOf('debug') !== -1) {
        Ext.getBody().addCls('x-debug');
    }

    var msg = function(title, msg) {
        Ext.Msg.show({
            title: title,
            msg: msg,
            minWidth: 200,
            modal: true,
            icon: Ext.Msg.INFO,
            buttons: Ext.Msg.OK
        });
    };

    

    var tpl = new Ext.XTemplate(
        'File processed on the server.<br />',
        'Name: {fileName}<br />',
        'Size: {fileSize:fileSize}'
    );
    Ext.create('Ext.form.Panel', {
        renderTo: 'extjs-upload-form',
        width: 500,
        frame: true,
        title: 'Publicar promo',
        bodyPadding: '10 10 0',
		jsonSubmit : true,
        defaults: {
            anchor: '100%',
            allowBlank: false,
            msgTarget: 'side',
            labelWidth: 50
        },

        items: [{
            xtype: 'filefield',
            id: 'form-file',
            emptyText: 'Selecciona la imagen.',
            fieldLabel: 'Imagen',
            name: 'photo-path',
            buttonText: '',
            buttonConfig: {
                iconCls: 'upload-icon'
            }
        }],

        buttons: [{
            text: 'Guardar',
            handler: function(){
                var form = this.up('form').getForm();
                if(form.isValid()){
					document.getElementById("promoImg").value = form.findField('form-file').getValue();	
                    form.submit({
                        url: 'http://six-mobile-app.herokuapp.com/upload',
                        waitMsg: 'Publicando promo...',
                        success: function(fp, o) {
                            $('#legend2').show();
							$('#step2').show();
							$('#step2-1').show();
							$('#step2-2').show();
							$('#step2-3').show();
							$('#step3').show();
							$('#finalstep').show();
							$('#step1').hide();
							$('#legend1').hide();
                        },
                        failure: function(fp, o) {
							$('#legend2').show();
							$('#step2').show();
							$('#step2-1').show();
							$('#step2-2').show();
							$('#step2-3').show();
							$('#step3').show();
							$('#finalstep').show();
							$('#step1').hide();
							$('#legend1').hide();
						}
                    });
                }
            }
        },{
            text: 'Reset',
            handler: function() {
                this.up('form').getForm().reset();
            }
        }]
    });

});
