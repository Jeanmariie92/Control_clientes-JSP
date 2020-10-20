<div class="col-3 ml-4">
    <div class="card">
        <div class="card-header">
            <h4>Cliente</h4>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/ControladorProducto?accion=buscarC"
                  method="POST" class="was-validated">
                <div class="form-group">
                    <div class="col-10 d-flex" >
                        <input type="text" class="form-control col-9" name="idCliente" placeholder="Codigo Cliente" required>
                        <div class="alert alert-primary" role="alert">
                            ${errorCliente}
                        </div>
                        <button class="btn btn-primary ml-4" type="submit">Buscar</button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-10 d-flex" >
                        <ul class="list-group">
                            <li class="list-group-item"><b>Nombre:</b> ${cliente.getNombre()} </li>
                            <br>
                            <li class="list-group-item"><b>Apellido:</b> ${cliente.getApellido()} </li>
                            <br>
                            <li class="list-group-item"><b>E-mail:</b> ${cliente.getEmail()} </li>
                            <br>
                            <li class="list-group-item"><b>Saldo:</b> ${cliente.getSaldo()} </li>
                            <br>

                            </div>
                            <div class="col-10 d-flex" >
                                <a href="${pageContext.request.contextPath}/ControladorProducto?accion=editar&idCliente=${cliente.idCliente}"
                                   class="btn btn-secondary mt-2">
                                    Editar
                                </a>
                            </div>
                        </ul>      
                    </div>
            </form>            
        </div>
    </div>

</div>