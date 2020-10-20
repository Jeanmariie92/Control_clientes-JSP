<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section id="usuarios" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Usuarios</h4>
                    </div>
                    <table class="table table-striped"> 
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                        <a href="../../web.xml"></a>
                                <th>Nombre y Apellido</th>
                                <th>ID usuario</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de usuarios -->
                            <c:forEach var="usuario" items="${usuarios}">
                                <tr>
                                    <td> ${status.count}</td>
                                    <td> ${usuario.nombre} </td>
                                    <td>${usuario.idUsuario}</td>
                                    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
                      
            </div>
 
              <div class="col-md-3">
                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Nuevo Usuario</h3>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
 

