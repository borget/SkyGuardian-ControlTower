<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SkyGuardian Security Services</title>
<link href="../js/extjs/resources/css/ext-all-gray.css" type="text/css" rel="stylesheet">

<style type="text/css">
/*!
 * Ext JS
 * Copyright(c) 2006-2012 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */
body {
    padding:20px;
    padding-top:32px;
}
.x-body {
    font-family:helvetica,tahoma,verdana,sans-serif;
    font-size:13px;
}
p {
    margin-bottom:15px;
}
h1 {
    font-size:18px;
    margin-bottom:20px;
}
h2 {
    font-size:14px;
    color:#333;
    font-weight:bold;
    margin:10px 0;
}
.example-info{
    width:150px;
    border:1px solid #c3daf9;
    border-top:1px solid #DCEAFB;
    border-left:1px solid #DCEAFB;
    background:#ecf5fe url( info-bg.gif ) repeat-x;
    font-size:10px;
    padding:8px;
}
pre.code{
    background: #F8F8F8;
    border: 1px solid #e8e8e8;
    padding:10px;
    margin:10px;
    margin-left:0px;
    border-left:5px solid #e8e8e8;
    font-size: 12px !important;
    line-height:14px !important;
}
.msg .x-box-mc {
    font-size:14px;
}
#msg-div {
    position:absolute;
    left:50%;
    top:10px;
    width:400px;
    margin-left:-200px;
    z-index:20000;
}
#msg-div .msg {
    border-radius: 8px;
    -moz-border-radius: 8px;
    background: #F6F6F6;
    border: 2px solid #ccc;
    margin-top: 2px;
    padding: 10px 15px;
    color: #555;
}
#msg-div .msg h3 {
    margin: 0 0 8px;
    font-weight: bold;
    font-size: 15px;
}
#msg-div .msg p {
    margin: 0;
}
.x-grid3-row-body p {
    margin:5px 5px 10px 5px !important;
}

.feature-list {
    margin-bottom: 15px;
}
.feature-list li {
    list-style: disc;
    margin-left: 17px;
    margin-bottom: 4px;
}
</style>

</head>
<body>
 <div id="encryptor-form"></div>

<script src="../js/extjs/ext-all.js"></script>

<script type="text/javascript">
Ext.require([
             'Ext.form.*',
             'Ext.data.*'
         ]);

         Ext.define('example.contact', {
             extend: 'Ext.data.Model',
             fields: [
                 {name: 'first', mapping: 'name > first'},
                 {name: 'last', mapping: 'name > last'},
                 'company', 'email', 'state',
                 {name: 'dob', type: 'date', dateFormat: 'm/d/Y'}
             ]
         });
             
         Ext.define('example.fielderror', {
             extend: 'Ext.data.Model',
             fields: ['id', 'msg']
         });

         Ext.onReady(function(){

             var formPanel = new Ext.form.Panel({
                 renderTo: 'encryptor-form',
                 frame: true,
                 title:'SkyGuardian Encryptor (Algorithm - PBEWithMD5AndTripleDES)',
                 width: 460,
                 bodyPadding: 7,
                 waitMsgTarget: true,

                 fieldDefaults: {
                     labelAlign: 'right',
                     labelWidth: 120,
                     msgTarget: 'side'
                 },

                 // configure how to read the XML data, using an instance
                 reader : new Ext.data.reader.Xml({
                     model: 'example.contact',
                     record : 'contact',
                     successProperty: '@success'
                 }),

                 // configure how to read the XML error, using a config
                 errorReader: {
                     type: 'xml',
                     model: 'example.fielderror',
                     record : 'field',
                     successProperty: '@success'
                 },

                 items: [{
                     xtype: 'fieldset',
                     title: 'Encrypt your password',
                     defaultType: 'textfield',
                     defaults: {
                         width: 395
                     },
                     items: [{
                    	 	id: 'password',
                             fieldLabel: 'Password',
                             emptyText: 'Enter a password',
                             name: 'password'
                         }, {
                        	 id: 'encryptedPassword',
                        	 name: 'encryptedPassword',
                        	 fieldLabel: 'Encrypted Password',
                        	 readOnly: true
                         }]
                 }],

                 buttons: [{
                     text: 'Encrypt',
                     disabled: true,
                     formBind: true,
                     handler: function(){
                    	 var form = this.up('form').getForm();
                         form.submit({
                             url: 'http://162.249.6.228:8080/SkyGuardianControlTower/service/encryptor/'+form.findField('password').getValue(),
                             method: 'GET',
                             waitMsg: 'Processing request...',
                             submitEmptyText: false,
                             success: function(form, action) {
                                 form.findField('encryptedPassword').setValue(action.response.responseXML.firstChild.firstChild.innerHTML);
                             },
                             
                         });
                     }
                 }]
             });

         });
</script>
</body>
</html>