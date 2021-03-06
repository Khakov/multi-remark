package com.kpfu.itis.khakov.multiremark.utils;

/**
 * @author Rustam Khakov
 */
public class ApplicationUrls {
	public static final String API_URL = "/api";
	public static final String TEST = API_URL + "/test";
	public static final String LOGIN = API_URL + "/login";
	public static final String WORK = API_URL + "/work";
	public static final String MAIN = API_URL + "/main";
	public static final String ADD_QUESTION = API_URL + "/questions/{taskId}";
	public static final String CREATE_TASK = API_URL + "/tasks";
	public static final String LOGOUT = API_URL + "/logout";
	public static final String GET_TASKS = API_URL + "/tasks";
	public static final String GET_STUDENTS = API_URL + "/students";
	public static final String GET_QUESTIONS = API_URL + "/questions";
	public static final String QUESTION_ANSWER = API_URL + "/questions/answer";
	public static final String GET_TASK = API_URL + "/tasks/{taskId}";
	public static final String GET_WORKS = API_URL + "/works";
	public static final String GET_TASKS_NOT_COMPLETED = API_URL + "/tasks/all";
	public static final String CREATE_STUDENT = API_URL + "/student";
	public static final String ADD_WORK = API_URL + "/work/{id}";
	public static final String GET_WORKS_BY_TASK = API_URL + "/works/{id}";
	public static final String GET_WORK_BY_TASK = API_URL + "/work/{id}";
	public static final String CREATE_COMMENT = API_URL + "/comment/{reviewId}";
	public static final String CREATE_REVIEW = API_URL + "/review/{workId}";
	public static final String GET_WORK_STAGES = API_URL + "/stages/{id}";
	public static final String GET_WORK_CODE = API_URL + "/code/{id}";
	public static final String GET_USER = API_URL + "/user";
	public static final String ADD_TEST_CASE = API_URL + "/test-case/{id}";
	public static final String ADD_TASK_STANDART = API_URL + "/standart/{id}";
	public static final String GET_WORK_STAGES_EDIT = API_URL + "/stage-edit/{id}";
	public static final String GET_WORK_STAGE = API_URL + "/stage/{id}";
	public static final String REVIEW_DONE = API_URL + "/review-done/{id}";
	public static final String WORK_MARK = API_URL + "/mark/{id}";
}
