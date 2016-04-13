package lt.virai.labanoroDraugai.domain.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import lt.virai.labanoroDraugai.domain.entities.AuthenticationAttribute;
import lt.virai.labanoroDraugai.domain.model.AuthAttributeEnum;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuthenticationAttribute.class)
public abstract class AuthenticationAttribute_ {

	public static volatile SingularAttribute<AuthenticationAttribute, AuthAttributeEnum> name;
	public static volatile SingularAttribute<AuthenticationAttribute, Integer> id;
	public static volatile SingularAttribute<AuthenticationAttribute, String> value;

}

