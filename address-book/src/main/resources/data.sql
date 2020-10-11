insert into address_book (id, address_book_name) values (1, 'Uni friends');

insert into contact (id, name, phone_number) values (1, 'Zeny', '04512343');
insert into contact (id, name, phone_number) values (2, 'Fred', '04512344');
insert into contact (id, name, phone_number) values (3, 'Alvin', '04512342');

insert into address_book_contacts (address_book_id, contacts_id) values (1, 1);
insert into address_book_contacts (address_book_id, contacts_id) values (1, 2);
insert into address_book_contacts (address_book_id, contacts_id) values (1, 3);