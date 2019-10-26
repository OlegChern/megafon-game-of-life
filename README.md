[![Build Status](https://travis-ci.org/xenomachina/kotlin-argparser-example.svg?branch=master)](https://travis-ci.com/OlegChern/Megafon-Game-Of-Life)

# Megafon Game Of Life

This repository contains a cross-platform implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) created specially for [Megafon 5G Dream Lab's](http://5gdreamlab.spbu.ru/) competition. 

## Prerequisites

To run the application JRE 1.8 or newer is required. 

## Usage

```
> java -jar application/GameOfLife.jar

+-------------------+
|      * * *        |
|      * * *        |
|        *          |
|                   |
|    * *            |
|  * *              |
|                   |
|                   |
|                   |
|                   |
+-------------------+
```

`GameOfLife` accepts some optional parameters you may want to play around with:

```
usage: [-h] [-c COLUMNS] [-r ROWS] [-s SEED] [-d DENSITY] [-u]

optional arguments:
  -h, --help          show this help message and exit

  -c COLUMNS,         Number of columns in the game field. Must be a positive
  --columns COLUMNS   integer.

  -r ROWS,            Number of rows in the game field. Must be a positive
  --rows ROWS         integer.

  -s SEED,            Seed to generate an initial game state from. Must be an
  --seed SEED         integer

  -d DENSITY,         Determines the amount of living cells in the initial
  --density DENSITY   state. Should be a float from 0 to 1. If value is given
                      from another range, it will be scaled to [0, 1].

  -u, --unbounded     Determines if the game field is enclosed or not.
```
