@ECHO OFF

echo =========================================

echo -		   			      -
echo -					      -
echo -			AppliAC
echo -					      -
echo =========================================



REM java -jar AppliAC.jar corbaloc:iiop:1.2@10.0.0.2:2001/NameService
java -jar AppliAC.jar corbaloc:iiop:1.2@localhost:2001/NameService

Echo Done


