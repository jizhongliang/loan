title "%~f0"
java -Xms200m -Xmx200m -XX:MetaspaceSize=200m -XX:MaxMetaspaceSize=200m -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=9997,server=y,suspend=n -jar loan-cloud-admin/target/loan-cloud-admin.jar 





 