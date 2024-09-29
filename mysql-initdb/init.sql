CREATE DATABASE IF NOT EXISTS my_database;
USE my_database;

-- Create Users Table
CREATE TABLE Users (
                       UserID INT AUTO_INCREMENT PRIMARY KEY,
                       Username VARCHAR(255) UNIQUE NOT NULL,
                       Password VARCHAR(255) NOT NULL,
                       Role int default 0 # ENUM(0:'User', 1:'Administrator')
);

-- Create Code Summarizations Table
CREATE TABLE CodeSummarizations (
                                    SummarizationID INT AUTO_INCREMENT PRIMARY KEY,
                                    UserID INT,
                                    CodeText TEXT NOT NULL,
                                    Summarization TEXT NOT NULL,
                                    CreationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Create Evaluations Table
CREATE TABLE Evaluations (
                             EvaluationID INT AUTO_INCREMENT PRIMARY KEY,
                             SummarizationID INT,
                             UserID INT,
                             NaturalnessScore int default 5,
                             UsefulnessScore int default 5,
                             ConsistencyScore int default 5,
                             Feedback TEXT,
                             EvaluationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                             FOREIGN KEY (SummarizationID) REFERENCES CodeSummarizations(SummarizationID),
                             FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Create File Uploads Table
CREATE TABLE FileUploads (
                             FileID INT AUTO_INCREMENT PRIMARY KEY,
                             UserID INT,
                             FilePath VARCHAR(255) NOT NULL,
                             UploadDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                             FOREIGN KEY (UserID) REFERENCES Users(UserID)
);


INSERT INTO Users (Username, Password, Role) VALUES ('admin', 'admin', 1);
