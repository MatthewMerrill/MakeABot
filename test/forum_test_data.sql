DELETE FROM forum_section;
INSERT INTO forum_section (id, name) VALUES (1, "General");
INSERT INTO forum_section (id, name) VALUES (2, "Off Topic");
INSERT INTO forum_section (id, name) VALUES (3, "Facebook Messenger");
INSERT INTO forum_section (id, name) VALUES (4, "Slack");

INSERT INTO forum_section (id, name, parent_section_id) VALUES (5, "Forum Games", 2);