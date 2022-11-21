<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="utf-8">
    <title>CKEditor 5 - Classic editor</title>
    <script src="https://cdn.ckeditor.com/ckeditor5/10.1.0/classic/ckeditor.js"></script>
</head>
<body>
    <h1>Classic editor</h1>
    <textarea name="content" id="editor">
        <p>This is some sample content.</p>
    </textarea>
    <script>
        ClassicEditor
            .create( document.querySelector( '#editor' )
            		, {
                ckfinder: {
                    uploadUrl: 'http://localhost:8080/base/ckeditor/file_upload.do'
                }}) ;
        
    </script>
</body>
</html>