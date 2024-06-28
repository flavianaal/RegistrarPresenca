
package programe.io.generico;

import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



public class ServicoGenerico<T> {
    
    private Class<T> entidade;
    
    @PersistenceContext
    public EntityManager entityManager; 
    
       
    public ServicoGenerico(Class<T> entidade){
        this.entidade = entidade;
    }
    
    public void salvar(T entidade){
        entityManager.persist(entidade); 
    }
    
    public void atualizar(T entidade){
        entityManager.merge(entidade);
    }
    
    public void delete(T entidade){
        entidade = entityManager.merge(entidade);
        entityManager.remove(entidade);
    }
    
    
    public T find(Long id){
        T objeto = getEntityManager().find(entidade, id);
        getEntityManager().refresh(objeto);
        return objeto;
    }

    public List<T> findAll(){
        return entityManager.createQuery("SELECT e FROM "+ entidade.getSimpleName()+" e WHERE e.ativo = true").getResultList();
    }
    

    public Class<T> getEntidade() {
        return entidade;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.entidade);
        hash = 23 * hash + Objects.hashCode(this.entityManager);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServicoGenerico<?> other = (ServicoGenerico<?>) obj;
        if (!Objects.equals(this.entidade, other.entidade)) {
            return false;
        }
        return Objects.equals(this.entityManager, other.entityManager);
    }

    @Override
    public String toString() {
        return "ServicoGenerico{" + "entidade=" + entidade + ", entityManager=" + entityManager + '}';
    }

   
    
    
}
