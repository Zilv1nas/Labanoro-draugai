package lt.virai.labanoroDraugai.domain.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import lt.virai.labanoroDraugai.domain.entities.AuthenticationAttribute;
import lt.virai.labanoroDraugai.domain.entities.User;
import lt.virai.labanoroDraugai.domain.model.UserRole;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

    public static volatile SingularAttribute<User, UserRole> role;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SetAttribute<User, AuthenticationAttribute> authenticationAttributes;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;

}

