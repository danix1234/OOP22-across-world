# maps

folder with files for loading all enitity for a specific `map`

## warning

- `player`:
    - always start in pos [~ xmax/2, ~ ymax/2]
    - position on the screen doesn't change when going up, or down (background does instead!)    
    

- `cars, trains, trunks`:
    - can start also at center of the map (maybe not trains)
    - must have two variants: 
        - `_SX` (going left)
        - `_DX` (going right)

- `water`:
    - need to appear as the following entity types:
        - `BackgroundCell`: to be drawn as background
        - `Obstacle`: to allow it being detected for the collisions

# template

[NAME_ENTITY1]    
x y     
x y     
...    

[NAME_ENTITY2]     
x y    
x y     
...     

# example

[TREE]   
0 0   
1 3      
5 10      

[GRASS]       
0 0       
1 0    
2 0    
3 0     
9 5     

[CAR_SX]     
4 1          
5 2     
