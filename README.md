# Labanoro-draugai
PSK

Projektas jau sukonfigintas, galima kažką pradėti daryti.

Kol kas pakūriau Azure mysql duombazę 20 mb. Max 4 connectionai vienu metu. Jei neužteks, vėliau kur nors kitur pahostinsim.

DB connectionas:
Į savo wildfly instaliacijos folderį įmeskit viską, kas projekto wildfly folderio viduje.

Tip pat į wildfly-XX\standalone\configuration\standalone.xml reikia prisidėti datasource'ą:
```xml             
<datasource jta="true" jndi-name="java:jboss/datasources/LabanoroDraugaiDS" pool-name="LabanoroDraugaiDS" enabled="true" use-java-context="true">
  <connection-url>jdbc:mysql://eu-cdbr-azure-north-d.cloudapp.net:3306/Labanoro-draugai-db</connection-url>
  <driver>mysql</driver>
  <security>
    <user-name>bcd67b9ab0937f</user-name>
    <password>7593a117</password>
  </security>
</datasource>
```

Ir driverį: 
```xml
<driver name="mysql" module="com.mysql">
  <xa-datasource-class>com.mysql.jdbc.Driver</xa-datasource-class>
</driver>
```

