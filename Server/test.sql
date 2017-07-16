-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 16, 2017 at 04:49 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` bigint(20) NOT NULL,
  `Question` text NOT NULL,
  `Option1` varchar(128) NOT NULL,
  `Option2` varchar(128) NOT NULL,
  `Option3` varchar(128) NOT NULL,
  `Option4` varchar(128) NOT NULL,
  `Answer` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `Question`, `Option1`, `Option2`, `Option3`, `Option4`, `Answer`) VALUES
(1, 'Which of the following terms are included inside the package?', 'classes', 'Method', 'Interfaces', 'all of the above.', 4),
(2, 'Executable applet is nothing but the ______  file of the applet.', '.class.', '.java', '.html', '.applet', 1),
(3, 'For interpretation of java program, ______ command is used.', 'java.', 'javac', 'javap', 'none of these', 1),
(4, 'Hot java is _____', 'system software', 'web browser.', 'java environment', 'IDE', 2),
(5, 'Arguments passed via command line are stored as _____ data type.', 'Data type of argument', 'integer', 'string.', 'double', 3),
(6, 'How many classes we are able to extend in java?', 'One.', 'Two', 'Three', 'None', 1),
(7, 'Who start the default thread available in java program?', 'System class', 'main method.', 'static keyword', 'none of these', 2),
(8, 'Condition which cause run time error in program is known as ______', 'Error', 'Exception.', 'Conditional ', 'None of These', 2),
(9, 'Suspend thread can be revived by using ___________ method.', 'start()', 'suspend()', 'resume()', 'yield()', 3),
(10, 'Java Intermediate code is known as _____', 'Byte code.', 'java code', 'reference code', 'none of these', 1),
(11, 'If A single program consists of three classes, then after compilation how many class file(s) is/are created by compiler?', 'One', 'Two', 'Three.', 'Four', 3),
(12, 'JVM is a _____ for byte code.', 'interpreter.', 'compiler', 'assembler', 'Debugger', 1),
(13, 'After the compilatio  of the java source code, _____ file is created by JVM.', 'class', 'java', '.cdr', '.doc', 1),
(14, 'Which is the first method to be called by the browser in applet?', 'start()', 'init().', 'main()', 'run()', 2),
(15, 'Which method of the Applet class displays the result of applet code on screen?', 'run()', 'paint().', 'drawString()', 'main()', 2),
(16, 'How many keywords are available in java?', '38', 'more than 50', '48.', '50', 4),
(17, 'All collection classes are available in _____ package.', 'java.io', 'java.lang', 'java.awt', 'java.util.', 4),
(18, '_____ method cannot be overridden.', 'super', 'static', 'final', 'println', 3),
(19, 'It is possible to add and delete object from _____ list as and when required?', 'vector.', 'array', 'both a and b', 'none of these', 1),
(20, 'Why to use array as a parameter in main method ?', 'it is syntax', 'it is easy to write', 'it stores multiple values.', 'none of these', 3),
(21, 'Methods of the interface are _____ in nature.', 'abstract', 'Concrete', 'variable', 'static', 1),
(22, 'What is jdb?', 'java compiler', 'java Database', 'java Disassemble', 'java debugger.', 4),
(23, 'Which method executes only once?', 'start()', 'init().', 'stop()', 'destroy()', 2),
(24, 'By default, how many minimum threads are available in program?', 'Zero', 'One.', 'Two', 'Uncountable', 3),
(25, 'In thread, which method resumes the first thread, that went into the sleep mode', 'wait()', 'notifyall()', 'notify().', 'resume()', 3),
(26, '_____ inheritance is achieved using interface in java.', 'multiple.', 'multilevel', 'Hierarchical', 'Single', 1),
(27, 'For creating dynamic array we have to use _____.', 'array', 'String', 'vector.', 'none of these', 3),
(28, 'Thread class available in which package?', 'java.io', 'java.lang', 'java.awt', 'java.util', 2),
(29, 'Which is the latest version on jdk?', '1.6', '1.7.', '1.8', '1.9', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
