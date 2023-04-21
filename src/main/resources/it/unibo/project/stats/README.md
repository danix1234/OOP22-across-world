# stats

folder with file with `statistics` to default to, if none are found in user home directory

- WARNING:
    - this file will change with time, so it can't be saved inside project directory.
    - inside the project directory, there will be only a single file to which application will default if none are found in home directory
    - handle logic to save and load files from user home directory
    - first skin `MUST` always be unlocked, otherwise player has no skin to play with!

## template

[coins]    
int    

[skins]    
int (quantity of skins)     
boolean      
boolean     
...    

## example 1

[`coins`]     
10    

[`skins`]    
3       
true    
false    
false   

## example 2

[`coins`]    
169    

[`skins`]     
5   
true    
false    
true    
false     
true     