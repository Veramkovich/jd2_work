1. Insert these lines into CATALINA_HOME/conf/tomcat-users.xml
<tomcat-users>
    <role rolename="manager-gui"/>
    <role rolename="manager-script"/>
    <user username="tomcat" password="tomcat" roles="manager-gui,manager-script"/>
</tomcat-users>