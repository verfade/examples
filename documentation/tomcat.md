# How to configure Tomcat to use resources outside the WAR
See [https://tomcat.apache.org/tomcat-7.0-doc/config/context.html#Virtual_webapp](https://tomcat.apache.org/tomcat-7.0-doc/config/context.html#Virtual_webapp).

Create a file `myWarName.xml` in the Tomcat directory `/conf/Catalina/localhost` with the following text:

```xml
<!-- https://tomcat.apache.org/tomcat-7.0-doc/config/context.html -->
<Context reloadable="true">
  <!-- https://tomcat.apache.org/tomcat-7.0-doc/config/loader.html#VirtualWebappLoader_Implementation -->
  <Loader className="org.apache.catalina.loader.VirtualWebappLoader"
          virtualClasspath="/Users/.../myProject/testResources/src/test/resources"/>
</Context>
```
