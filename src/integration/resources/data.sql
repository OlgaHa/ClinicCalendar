INSERT INTO `doctors_tbl` (`doctor_id`, `name`, `specialization`) VALUES
	(1, 'Derek Shepherd', 'neurosurgeon'),
	(2, 'Mark Sloan', 'plastic surgeon'),
	(3, 'Owen Hunt', 'trauma surgeon'),
	(4, 'Arizona Robbins', 'pediatric surgeon'),
	(5, 'Meredith Grey', 'general surgeon'),
	(6, 'Miranda Bailey', 'Chief of Surgery');

INSERT INTO `appointments_tbl` (`id`, `appointment_time`, `doctor_id`, `patient_name`) VALUES
	(1, '2021-03-16 14:20', 1, 'Dave Grohl'),
	(2, '2021-01-14 10:00', 1, 'Josh Homme'),
	(3, '2021-02-21 10:00', 1, 'Kurt Cobain'),
	(4, '2021-02-16 10:00', 2, 'Math Bellamy'),
	(5, '2021-04-01 15:00', 2, 'Chris Wolstenholme'),
	(6, '2021-05-23 13:00', 2, 'Dominic Howard'),
	(7, '2021-02-16 10:00', 3, 'Ozzy Osbourne'),
	(8, '2021-02-18 10:00', 4, 'Billie Eilish'),
	(9, '2021-06-09 12:30', 5, 'Freddie Mercury');