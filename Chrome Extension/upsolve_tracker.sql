-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2018 at 03:21 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.1.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `upsolve_tracker`
--

-- --------------------------------------------------------

--
-- Table structure for table `1`
--

CREATE TABLE `1` (
  `link` varchar(255) NOT NULL,
  `tag` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `1`
--

INSERT INTO `1` (`link`, `tag`, `time`, `status`) VALUES
('https://codeforces.com/problemset/problem/1070/f', 'graph', 'Wed Oct 24 2018', 'SOLVED'),
('https://codeforces.com/problemset/problem/1070/a', 'greedy', 'Wed Oct 24 2018', 'PENDING'),
('https://codeforces.com/problemset/problem/1070/l', 'aa', 'Wed Oct 24 2018', 'PENDING');

-- --------------------------------------------------------

--
-- Table structure for table `5bd087087c002`
--

CREATE TABLE `5bd087087c002` (
  `link` varchar(255) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `5bd087087c002`
--

INSERT INTO `5bd087087c002` (`link`, `tag`, `time`, `status`) VALUES
('https://www.hackerearth.com/practice/basic-programming/input-output/basics-of-input-output/practice-problems/algorithm/tds-and-his-breakup/', 'Segment trees', 'Thu Oct 25 2018', 'PENDING'),
('https://codeforces.com/problemset/problem/1070/D', 'dp', 'Fri Oct 26 2018', 'SOLVED'),
('https://codeforces.com/problemset/problem/1070/B', 'dp', 'Fri Oct 26 2018', 'SOLVED'),
('https://codeforces.com/problemset/problem/1070/A', 'dp', 'Fri Oct 26 2018', 'SOLVED');

-- --------------------------------------------------------

--
-- Table structure for table `5bd2f6128ef76`
--

CREATE TABLE `5bd2f6128ef76` (
  `link` varchar(255) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `5bd32e5735a45`
--

CREATE TABLE `5bd32e5735a45` (
  `link` varchar(255) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `5bd32e5735a45`
--

INSERT INTO `5bd32e5735a45` (`link`, `tag`, `time`, `status`) VALUES
('https://codeforces.com/problemset/problem/1003/D', 'dp', 'Sat Oct 27 2018', 'SOLVED'),
('https://codeforces.com/problemset/problem/1013/B', 'dp', 'Sat Oct 27 2018', 'PENDING'),
('https://codeforces.com/problemset/problem/1013/C', 'graph', 'Sat Oct 27 2018', 'PENDING'),
('https://codeforces.com/problemset/problem/1013/D', 'Segment trees', 'Sat Oct 27 2018', 'SOLVED');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `question` varchar(500) DEFAULT NULL,
  `opA` varchar(25) DEFAULT NULL,
  `opB` varchar(25) DEFAULT NULL,
  `opC` varchar(25) DEFAULT NULL,
  `opD` varchar(25) DEFAULT NULL,
  `correctOp` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `tag`, `question`, `opA`, `opB`, `opC`, `opD`, `correctOp`) VALUES
(1, 'dp', 'sample dp', 'dp1', 'dp2', 'dp3', 'dp4', 'dp3'),
(2, 'Segment trees', 'sample segment trees', 'st1', 'st2', 'st3', 'st4', 'st2'),
(3, 'graph', 'graph sample', 'gr1', 'gr2', 'gr3', 'gr4', 'gr1');

-- --------------------------------------------------------

--
-- Table structure for table `upload`
--

CREATE TABLE `upload` (
  `user` varchar(255) NOT NULL,
  `tableId` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `upload`
--

INSERT INTO `upload` (`user`, `tableId`) VALUES
('Test@gmail.com', '1'),
('abc@gmail.com', '5bd087087c002'),
('tar', '5bd2f6128ef76'),
('par', '5bd32e5735a45');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rating` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `user`, `password`, `rating`) VALUES
(1, 'Test@gmail.com', '12345678', 0),
(4, 'abc@gmail.com', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 0),
(6, 'tar', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 0),
(7, 'par', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 24);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `1`
--
ALTER TABLE `1`
  ADD PRIMARY KEY (`link`);

--
-- Indexes for table `5bd087087c002`
--
ALTER TABLE `5bd087087c002`
  ADD PRIMARY KEY (`link`);

--
-- Indexes for table `5bd2f6128ef76`
--
ALTER TABLE `5bd2f6128ef76`
  ADD PRIMARY KEY (`link`);

--
-- Indexes for table `5bd32e5735a45`
--
ALTER TABLE `5bd32e5735a45`
  ADD PRIMARY KEY (`link`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `upload`
--
ALTER TABLE `upload`
  ADD PRIMARY KEY (`user`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
