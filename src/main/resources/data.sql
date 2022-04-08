insert into User (first_name, last_name, username, email_id, password) values ('Nickolas', 'Telega', 'ntelega0', 'ntelega0@ebay.com', 'UUfXwzHlbU');
insert into User (first_name, last_name, username, email_id, password) values ('Daffie', 'Bandiera', 'dbandiera1', 'dbandiera1@washingtonpost.com', 'uJsWm5');
insert into User (first_name, last_name, username, email_id, password) values ('Dane', 'Rennles', 'drennles2', 'drennles2@nytimes.com', '8yNpDZazDX5');
insert into User (first_name, last_name, username, email_id, password) values ('Norby', 'Morehall', 'nmorehall3', 'nmorehall3@weebly.com', 'RNg8CpFUg');
insert into User (first_name, last_name, username, email_id, password) values ('Meg', 'Bantock', 'mbantock4', 'mbantock4@studiopress.com', 'eiQB5kS8L');

insert into game (start_time, end_time, title, image_url, game_play_url) values ('2021-09-06 20:08:12', '2021-09-12 16:24:54', 'Ludo', 'game-images/ludo.png', 'ludo-url');
insert into game (start_time, end_time, title, image_url, game_play_url) values ('2021-11-19 05:49:07', '2021-12-01 09:40:55', 'Solitaire', 'game-images/solitaire.png', 'solitaire-url');
insert into game (start_time, end_time, title, image_url, game_play_url) values ('2021-10-05 11:43:44', '2021-11-29 09:40:01', 'Chess', 'game-images/chess.jpg', 'chess-url');
insert into game (start_time, end_time, title, image_url, game_play_url) values ('2021-08-03 03:53:11', '2021-08-26 13:56:13', 'Cricket', 'game-images/Cricket.jpg', 'cricket-url');
insert into game (start_time, end_time, title, image_url, game_play_url) values ('2021-08-16 22:54:41', '2021-08-29 20:29:35', 'Fruit Ninja', 'game-images/fruit-ninja.png', 'fruit-ninja-url');
--insert into game (start_time, end_time, title, image_url) values ('2021-03-16 03:03:30', '2021-06-12 11:47:33', 'CallBreak', null);

insert into game_category(game_category) values ('Sports');
insert into game_category(game_category) values ('Arcade');
insert into game_category(game_category) values ('Strategy');
insert into game_category(game_category) values ('Shooter');
insert into game_category(game_category) values ('Adventure');

insert into game_transaction(entry_coins,pool_size,winning_amount,game_ref) values (100,30,300,1);
insert into game_transaction(entry_coins,pool_size,winning_amount,game_ref) values (120,25,400,2);
insert into game_transaction(entry_coins,pool_size,winning_amount,game_ref) values (150,25,500,3);
insert into game_transaction(entry_coins,pool_size,winning_amount,game_ref) values (200,25,1000,4);
insert into game_transaction(entry_coins,pool_size,winning_amount,game_ref) values (250,20,1200,5);

insert into game_category_mapping(game_id,game_category_id) values (1,3);
insert into game_category_mapping(game_id,game_category_id) values (2,2);
insert into game_category_mapping(game_id,game_category_id) values (3,3);
insert into game_category_mapping(game_id,game_category_id) values (4,1);
insert into game_category_mapping(game_id,game_category_id) values (5,2);

insert into user_game_registration(game_transaction_id,user_id) values (1,1);
insert into user_game_registration(game_transaction_id,user_id) values (2,3);
insert into user_game_registration(game_transaction_id,user_id) values (1,3);
insert into user_game_registration(game_transaction_id,user_id) values (2,4);
insert into user_game_registration(game_transaction_id,user_id) values (3,1);
insert into user_game_registration(game_transaction_id,user_id) values (3,4);
insert into user_game_registration(game_transaction_id,user_id) values (4,1);
insert into user_game_registration(game_transaction_id,user_id) values (4,2);
insert into user_game_registration(game_transaction_id,user_id) values (5,3);