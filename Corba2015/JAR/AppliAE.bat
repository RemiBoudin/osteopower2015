@ECHO OFF

echo =========================================

echo -		   			      -
echo -					      -
echo -			AppliAE
echo -					      -
echo =========================================



REM java -jar AppliAE.jar corbaloc:iiop:1.2@10.0.0.2:2001/NameService
java -jar AppliAE.jar corbaloc:iiop:1.2@localhost:2001/NameService

Echo Done


