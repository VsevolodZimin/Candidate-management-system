Set WshShell = CreateObject("Wscript.Shell")
WshShell.Run chr(34) & ".\batchFile.bat" & chr(34), 0
Set WshShell = Nothing