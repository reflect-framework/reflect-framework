@echo off
echo
echo
echo Dit zal de belangrijkste documenten uit my documents copieren naar een externe drive
echo Controleer of G: drive gelinked is aan de externe drive of annuleer met crtl-c
pause
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Documents" "g:\My Documents"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My E Books" "g:\My E Books"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Galaxi" "g:\My Galaxi"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Java" "g:\My Java"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Music" "g:\My Music"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Outlook Archives" "g:\My Outlook Archives"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Pictures" "g:\My Pictures"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Videos" "g:\My Videos"
