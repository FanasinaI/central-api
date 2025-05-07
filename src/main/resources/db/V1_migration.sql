-- Supprime la base si elle existe
DROP DATABASE IF EXISTS fifa_central;

-- Crée la base
CREATE DATABASE fifa_central;

-- Connexion à la nouvelle base
\c fifa_central;

-- Supprime les types existants
DROP TYPE IF EXISTS player_position;
DROP TYPE IF EXISTS duration_unit;
DROP TYPE IF EXISTS championship_name;

-- Crée les types énumérés
CREATE TYPE player_position AS ENUM ('STRIKER', 'MIDFIELDER', 'DEFENDER', 'GOAL_KEEPER');
CREATE TYPE duration_unit AS ENUM ('SECOND', 'MINUTE', 'HOUR');
CREATE TYPE championship_name AS ENUM ('PREMIER_LEAGUE', 'LA_LIGA', 'BUNDESLIGA', 'SERIA', 'LIGUE_1');

-- Suppression des tables si elles existent
DROP TABLE IF EXISTS player_statistics;
DROP TABLE IF EXISTS player_ranking;
DROP TABLE IF EXISTS club_ranking;
DROP TABLE IF EXISTS match_score;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS club;
DROP TABLE IF EXISTS coach;
DROP TABLE IF EXISTS championship;
DROP TABLE IF EXISTS playing_time;

-- Table des championnats
CREATE TABLE championship (
                              id int PRIMARY KEY,
                              name championship_name NOT NULL
);

-- Table des entraîneurs
CREATE TABLE coach (
                       id TEXT PRIMARY KEY,
                       name TEXT NOT NULL,
                       nationality TEXT NOT NULL
);

-- Table des clubs
CREATE TABLE club (
                      id TEXT PRIMARY KEY,
                      name TEXT NOT NULL,
                      acronym TEXT,
                      year_creation INTEGER,
                      stadium TEXT,
                      coach_id TEXT REFERENCES coach(id)
);

-- Table des joueurs
CREATE TABLE player (
                        id TEXT PRIMARY KEY,
                        name TEXT NOT NULL,
                        position player_position NOT NULL,
                        nationality TEXT NOT NULL,
                        age INTEGER,
                        number INTEGER,  -- Colonne 'number' ajoutée ici
                        club_id TEXT REFERENCES club(id)
);

-- Table des statistiques des joueurs (pour résoudre les 404)
CREATE TABLE player_statistics (
                                   id TEXT PRIMARY KEY,
                                   player_id TEXT REFERENCES player(id),
                                   season_year INTEGER NOT NULL,
                                   goals_scored INTEGER DEFAULT 0,
                                   assists INTEGER DEFAULT 0,
                                   yellow_cards INTEGER DEFAULT 0,
                                   red_cards INTEGER DEFAULT 0,
                                   UNIQUE(player_id, season_year)
);

-- Table des temps de jeu
CREATE TABLE playing_time (
                              id TEXT PRIMARY KEY,
                              value DOUBLE PRECISION NOT NULL,
                              duration_unit duration_unit NOT NULL
);

-- Table des classements de clubs
CREATE TABLE club_ranking (
                              id TEXT PRIMARY KEY,
                              championship_id int NOT NULL REFERENCES championship(id),
                              club_id TEXT NOT NULL REFERENCES club(id),
                              rank INTEGER DEFAULT 0,
                              ranking_points INTEGER NOT NULL,
                              scored_goals INTEGER NOT NULL,
                              conceded_goals INTEGER NOT NULL,
                              difference_goals INTEGER NOT NULL,
                              clean_sheet_number INTEGER NOT NULL,
                              season_year INTEGER NOT NULL,
                              UNIQUE (championship_id, club_id, season_year)
);

-- Table des classements des joueurs
CREATE TABLE player_ranking (
                                id TEXT PRIMARY KEY,
                                player_id TEXT REFERENCES player(id),
                                rank INTEGER,
                                championship_id int REFERENCES championship(id),
                                scored_goals INTEGER,
                                playing_time_id TEXT REFERENCES playing_time(id),
                                season_year INTEGER,
                                UNIQUE(player_id, championship_id, season_year)
);

-- Table des scores de match
CREATE TABLE match_score (
                             id TEXT PRIMARY KEY,
                             match_id TEXT,
                             home_score INTEGER,
                             away_score INTEGER
);

-- Insertion des données de base
INSERT INTO championship (id, name) VALUES
                                        ('1', 'PREMIER_LEAGUE'),
                                        ('2', 'LA_LIGA'),
                                        ('3', 'BUNDESLIGA'),
                                        ('4', 'SERIA'),
                                        ('5', 'LIGUE_1');
