# Labanoro-draugai
PSK

DB connectionas:
Į savo wildfly instaliacijos folderį įmeskit viską, kas projekto wildfly folderio viduje.

Tip pat į wildfly-XX\standalone\configuration\standalone.xml reikia prisidėti datasource'ą:
```xml             
<datasource jta="true" jndi-name="java:jboss/datasources/LabanoroDraugaiDS" pool-name="LabanoroDraugaiDS" enabled="true" use-java-context="true">
  <connection-url>jdbc:mysql://46.101.118.159:3306/Labanoro-draugai-db</connection-url>
  <driver>mysql</driver>
  <security>
    <user-name>root</user-name>
    <password>vyriukai</password>
  </security>
</datasource>
```

Ir driverį: 
```xml
<driver name="mysql" module="com.mysql">
  <xa-datasource-class>com.mysql.jdbc.Driver</xa-datasource-class>
</driver>
```

