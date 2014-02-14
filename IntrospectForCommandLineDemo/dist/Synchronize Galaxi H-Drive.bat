@echo off
echo
echo
echo On Galaxi S2: Instellingen, Draadloos en netwerk, USB hulp programmas, USB-mass opslag aanzetten
echo Controleer of H: drive gelinked is aan de Micro-SD kaart of annuleer met crtl-c
pause
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Galaxi\Media" "H:\Media"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My E Books" "H:\My E Books"
java -jar SynchronizeFolders.jar synchronize "C:\Documents and Settings\nilsth\My Documents\My Music" "H:\My Music"
