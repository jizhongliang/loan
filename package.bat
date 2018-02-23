rem mvn clean package  dependency:sources dependency:resolve  -U -Dmaven.test.skip=true -Pproduct -e
mvn clean package -Dmaven.test.skip=true -U -pl  loan-bestsign/ -am -Pproduct -e
pause

rem     dependency:sources dependency:resolve    -Ddownloadsources=true -Ddownloadjavadocs=true 