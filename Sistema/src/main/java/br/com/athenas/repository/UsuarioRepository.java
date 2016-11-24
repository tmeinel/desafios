package br.com.athenas.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;

import br.com.athenas.model.UsuarioModel;

/**
 * @author: tmeinel
 * 
 * CLASSE QUE VAI REALIZAR A PERSIST�NCIA DO OBJETO UsuarioModel NO BANCO DE DADOS.
 * 
 * 
 */
@Repository
public class UsuarioRepository {

	/**
	 * @PersistenceContext � o local onde ficam armazenados as entidades 
	 * que est�o sendo manipuladas pelo EntityManager
	 * 
	 * 
	 *@PersistenceContext(type = PersistenceContextType.EXTENDED) assim o Wildfly vai 
	 *gerenciar	para nos as entidades.
	 * 
	 **/
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	
	/**
	 * 
	 * @param usuarioModel
	 * 
	 * Salva um novo registro
	 * 
	 * O JPA exige um contexto de transa��o para realizar as altera��es, por isso vamos
	 * usar a anota��o @javax.transaction.Transactional
	 *  
	 * */
	@javax.transaction.Transactional
	public void Salvar(UsuarioModel usuarioModel){
		try {
			manager.persist(usuarioModel);		
			
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * 
	 * @param usuarioModel
	 * 
	 * Realiza a altera��o de um registro
	 */
	@javax.transaction.Transactional
	public void Alterar(UsuarioModel usuarioModel){
				
		manager.merge(usuarioModel);		
	}
	
	/**
	 * 
	 * @param codigo
	 * @return UsuarioModel
	 * 
	 * Consulta um usu�rio por c�digo
	 */	
	public UsuarioModel ConsultarPorCodigo(int codigo){
		
		return manager.find(UsuarioModel.class, codigo);		
	} 
	
	/**
	 * 
	 * @param codigo
	 * 
	 * Exclui um usu�rio pelo c�digo
	 */
	@javax.transaction.Transactional
	public void Excluir(int codigo){
		
		UsuarioModel usuarioModel = this.ConsultarPorCodigo(codigo);
		
		manager.remove(usuarioModel);
				
	}
	
	/**
	 * 
	 * @return List<UsuarioModel>
	 * 
	 * Consulta todos os usu�rios cadastrados no banco de dados
	 */
	public List<UsuarioModel> TodosUsuarios(){
		
		return manager.createQuery("SELECT c FROM UsuarioModel c ORDER BY c.nome ", UsuarioModel.class).getResultList();	
	}
	
}
