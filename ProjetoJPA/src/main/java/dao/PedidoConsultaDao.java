package dao;

import model.PedidoConsulta;
import util.JPAUtil;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

public class PedidoConsultaDao {
    private EntityManager em;

    public PedidoConsultaDao (EntityManager em) {
        this.em = em;
    }

    public List<PedidoConsulta>  buscarTodos() {
        String jpql = "SELECT p FROM PedidoConsulta p";
        return em.createQuery(jpql, PedidoConsulta.class).getResultList();
    }
}
