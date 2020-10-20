<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_AR"/>

<div class="container-fluid mt-2">
    <div class="row no-gutters"> 
        <jsp:include page="/WEB-INF/paginas/cliente/busquedaCliente.jsp"/>  
        <div class="col-8 ml-4">
            <div class="card">
                <div class="card-header">
                    <h4>Productos</h4>
                </div>
                <div class="card-body">
                    <form action="ControladorProducto" 
                          method="POST" class="was-validated">

                        <div class="form-group d-flex ">

                            <input type="text" class="form-control col-4" name="idProducto" placeholder="Codigo Producto">
                            <button class="btn btn-primary ml-2 col-2" type="submit" name="accion" 
                                    value="buscarP"> Buscar </button>
                        </div>

                        <div class="form-row border mt-2">

                            <div class="form-group ml-1 mt-4">
                                <input type="text" class="form-control" name="idProducto_" value="${producto.getIdProducto()}" readonly>
                                <small id="codigoPr" class="form-text text-muted">Codigo de Producto</small>
                            </div>
                            <div class="form-group ml-1  mt-4">
                                <input type="text" class="form-control" name="nombreProducto" value="${producto.getNombre()}" readonly>
                                <small id="nombrePr" class="form-text text-muted">Nombre de Producto</small>
                            </div>
                            <div class="form-group ml-1  mt-4">
                                <input type="text" class="form-control" name="precio" value="${producto.getPrecio()}" readonly>
                                <small id="precioPr" class="form-text text-muted">Precio de Producto</small>
                            </div>
                            <div class="form-group ml-1 mt-4">
                                <input type="number"  class="form-control" name="cantidad" value="0">
                                <small id="Cantidad" class="form-text text-muted">Cantidad</small>
                            </div>
                        </div>


                        <button class="btn btn-primary mt-4 " type="submit" name="accion" 
                                value="agregarP">
                            Agregar
                        </button>


                        <table class="table table-striped mt-2 border text-center"> 
                            <thead class="thead-dark">
                                <tr>

                                    <th>Cod</th>
                                    <th>Nombre</th>
                                    <th>Precio</th>
                                    <th>Cantidad</th> 
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Iteramos cada elemento de la lista de cientes -->
                                <c:forEach var="venta" items="${lista}" varStatus="status">
                                    <tr>

                                        <td>${venta.getIdProducto()}</td>
                                        <td> ${venta.getNombreProducto()}</td>
                                        <td>  ${venta.getPrecio()}</td>
                                        <td>  ${venta.getCantidad()}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/ControladorProducto?accion=eliminar&idProductolista=${venta.getIdProducto()}"
                                               class="btn btn-secondary"> <i class="fas fa-trash"></i> </a>
                                        </td>
                                    </tr>

                                </c:forEach>

                            </tbody>


                        </table>



                        <div class="card-footer">
                            <button class="btn btn-danger float-right ml-2" type="submit" name="accion" value="cancelar"> 
                                Cancelar </button>

                            <button class="btn btn-success  float-right ml-2" type="submit" name="accion" value="generarV" >
                                Registrar </button>
                            <button type="button" class="btn btn-primary">
                               Total <span class="badge badge-light">   ${total} </span>
                                <span class="sr-only">unread messages</span>
                            </button>


                        </div>
                    </form>
                </div>
            </div>                      
        </div>
    </div>
</div>



