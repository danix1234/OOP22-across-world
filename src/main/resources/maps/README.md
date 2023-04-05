# maps

folder with files for loading all enitity for a specific `map`

# template

// player always start in pos [~ xmax/2,0]

[BACKGROUND]
[BackgroundType 1]
x y
x y
...
[BackgroundType 2]
x y
x y
...

[COLLECTABLE]
[CollectableType 1]
x y
...
[CollectableType 2]
x y
...

[STATIC_OBSTACLE]
[ObstacleType 1]
x y
x y
...
[ObstacleType 2]
x y
x y

// cells from which they spawn -> only on left/right border -> they move to the right/left depending on where they spwan
[DYNAMIC_OBSTACLE] 
[ObstacleType 1]
x y 
x y
...

## example

[BACKGROUND]
[GRASS]
0 0
1 0
2 0
[STREET]
0 1
1 1
2 1
[RAIL]
0 2
1 2
2 2

[COLLECTABLE]
[COIN]
4 0
1 0
[POWERUP_COIN_MULTIPLIER]
1 1
2 3
[POWERUP_COIN_MAGNET]
0 9
1 3
[POWERUP_IMMORTALITY]
1 2
2 2

[STATIC_OBSTACLE]
[TREE]
3 4
5 6

[ROCK]
1 3
4 5

[DYNAMIC_OBSTACLE]
[CAR]
0 1
15 2
[TRAIN]
0 2
0 10
[TRUNK]
0 15
15 1
