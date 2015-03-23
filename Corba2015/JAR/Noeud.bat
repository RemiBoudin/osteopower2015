@ECHO OFF

echo =========================================
echo -		   			      -
echo -					      -
echo -			Noeud
echo -					      -
echo =========================================

start cmd /K call AppliAC.bat
REM java -jar AppliAC.jar corbaloc:iiop:1.2@10.0.0.2:2001/NameServic

start cmd /K call AppliAE
REM java -jar AppliAE.jar corbaloc:iiop:1.2@10.0.0.2:2001/NameService

start cmd /K call AppliAV
REM java -jar AppliAV.jar corbaloc:iiop:1.2@10.0.0.2:2001/NameService

Echo Done


