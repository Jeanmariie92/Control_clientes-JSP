<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>


        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <!--Custom styles-->
        <script src="js/jquery-1.11.1.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/3fb1c1a039.js" crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script
            src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3>Sign In</h3>
                        imagen
                    </div>
                    <div class="card-body">
                        <form id="form" 
                              method="POST" class="was-validated" action="ControladorLogin" >
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input id ="usuario" type="text" class="form-control" placeholder="username" name="usuario" required>
                                <div id = "mensaje"><small id="usuario" class="form-text text-muted"></small></div>
                            </div>
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" class="form-control" placeholder="password" name="password" required>
                            </div>
                            <div class="row align-items-center remember">
                                <input type="checkbox">Remember Me
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary" type="submit" id="enviar" >Ingresar</button>
                            </div>
                            <div class="alert alert-secondary" role="alert" id="alertasi">
                            </div>
                        </form>

                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center links">
                            Don't have an account?<a href="#">Sign Up</a>
                        </div>
                        <div class="d-flex justify-content-center">
                            <a href="#">Forgot your password?</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery.validate.min.js" type="text/javascript"></script>
        <script type="text/javascript">

            var msg = null;
            $(document).ready(function(){
                $("#alertasi").hide();
            
            $(function () {
            
                $('#form').validate({
                    submitHandler: function (form) {
                        var data = $("#form").serialize();

                        $.ajax({
                            type: 'POST',
                            url: "ControladorLogin",
                            data: data,
                            dataType: 'JSON',

                            success: function (data) {

                                msg = data[0].msj;

                                if (msg == 1) {

                                    window.location.replace("principal.jsp");
                                } else if (msg == 3) {
                                    $("#alertasi").hide().html("Usuario no encontrado").fadeIn('slow');
                                }
                            }

                        });
                    }});
            });
        });
        </script>
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"/>

    </body>

</html>