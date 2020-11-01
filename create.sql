CREATE DATABASE wild;
\c wild
CREATE TABLE animals(id SERIAL PRIMARY KEY,health varchar, age varchar, type varchar,name varchar, threattype varchar);
CREATE TABLE locations(id SERIAL PRIMARY KEY, name varchar);
CREATE TABLE rangers(id SERIAL PRIMARY KEY, rangerName varchar , rangerId int , rangerbadge int);
CREATE TABLE sightings(id SERIAL PRIMARY KEY, ranger varchar , location varchar, animal varchar );