INSERT INTO sfseal_user.account (account_id, username, password, phone_number, email, create_date, last_update_date, last_update_user)
VALUES (-1,'SystemAdmin','196976f7daa577074b781a3742c21a002e5bdaf7','18640153156','Daniel@sfseal.net',current_timestamp,current_timestamp,'System');
INSERT INTO sfseal_user.staff (account_id, name, mobile, office_phone_number, address, is_active, create_date, last_update_date, last_update_user) VALUES
(-1,'郭崇伦','18640153156',null,null,true,current_timestamp,current_timestamp,'System');
INSERT INTO sfseal_user.account_role_xref (account_id, role_id, create_date, last_update_date, last_update_user) VALUES
(-1,1,current_timestamp,current_timestamp,'System');
INSERT INTO sfseal_company.company (company_id, company_name, phone_number, address, email, create_date, last_update_date, last_update_user) VALUES
(1,'沈阳耀福施封锁有限公司','18640153156','辽宁省沈阳市','Daniel@sfseal.net',current_timestamp,current_timestamp,'System');
INSERT INTO sfseal_company.company_account_xref(company_id, account_id, create_date, last_update_date, last_update_user) VALUES
(1,-1,current_timestamp,current_timestamp,'System');

alter sequence sfseal_company.company_company_id_seq restart with 2;