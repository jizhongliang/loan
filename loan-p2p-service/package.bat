call mvn clean package -Ddownloadsources=true -Ddownloadjavadocs=true -Dmaven.test.skip=true -Pdev -e
rem mvn clean package -Pdev -Dmaven.test.skip=true -e -U -pl  loan-borrow/ -am
pause