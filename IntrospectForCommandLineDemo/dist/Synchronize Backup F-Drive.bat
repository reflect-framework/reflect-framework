@echo off
echo
echo
echo Dit zal de belangrijkste documenten uit my documents copieren naar een externe drive
echo Controleer of F: drive gelinked is aan de externe drive of annuleer met crtl-c
pause
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Documents" "f:\My Documents"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My E Books" "f:\My E Books"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Galaxi" "f:\My Galaxi"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Java" "f:\My Java"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Music" "f:\My Music"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Outlook Archives" "f:\My Outlook Archives"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Pictures" "f:\My Pictures"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Videos" "f:\My Videos"
