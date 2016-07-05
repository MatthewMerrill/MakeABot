
DELETE FROM forum_comment;
DELETE FROM forum_post;
DELETE FROM forum_section WHERE id=5;
DELETE FROM forum_section;

INSERT INTO forum_section (id, name) VALUES (1, "General");
INSERT INTO forum_section (id, name) VALUES (2, "Off Topic");
INSERT INTO forum_section (id, name) VALUES (3, "Facebook Messenger");
INSERT INTO forum_section (id, name) VALUES (4, "Slack");
INSERT INTO forum_section (id, name, parent_section_id) VALUES (5, "Forum Games", 2);

INSERT INTO forum_post (id, name, parent_section_id) VALUES (1, "State Game", 5);

INSERT INTO forum_comment (id, message_body, parent_post_id) VALUES (1, "California", 1);
INSERT INTO forum_comment (id, message_body, parent_post_id) VALUES (2, "Alaska", 1);
INSERT INTO forum_comment (id, message_body, parent_post_id) VALUES (3, "Alabama", 1);
INSERT INTO forum_comment (id, message_body, parent_post_id) VALUES (4, "Arkansas", 1);