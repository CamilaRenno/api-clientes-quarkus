package br.com.exemplo.cliente;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @GET
    public List<Cliente> listAll() {
        return Cliente.listAll();
    }

    @GET
    @Path("/{id}")
    public Cliente findById(@PathParam("id") Long id) {
        Cliente c = Cliente.findById(id);
        if (c == null) throw new NotFoundException("Cliente não encontrado");
        return c;
    }

    @POST
    @Transactional
    public Response create(@Valid Cliente cliente) {
        cliente.id = null;
        cliente.persist();
        return Response.created(URI.create("/clientes/" + cliente.id)).entity(cliente).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Cliente update(@PathParam("id") Long id, @Valid Cliente dados) {
        Cliente c = Cliente.findById(id);
        if (c == null) throw new NotFoundException("Cliente não encontrado");
        c.nome = dados.nome;
        c.email = dados.email;
        c.telefone = dados.telefone;
        return c;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Cliente.deleteById(id);
        if (!deleted) throw new NotFoundException("Cliente não encontrado");
        return Response.noContent().build();
    }
}