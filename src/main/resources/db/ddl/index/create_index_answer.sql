CREATE INDEX idx_answer_quiz_id ON quiz (quiz_id);
CREATE INDEX idx_answer_quiz_id_answer_id ON quiz (quiz_id, answer_id);