import axios from 'axios';

const BOARD_API_BASE_URL = "http://localhost:8080/api/board"; 
const LOGIN_API_BASE_URL = "http://localhost:8080/auth/login";

class BoardService {

    getBoards(p_num) {
        return axios.get(BOARD_API_BASE_URL + "?p_num="+ p_num);
    }

    createBoard(board) {
        return axios.post(BOARD_API_BASE_URL,board, { headers: {"Content-Type": "multipart/form-data"} });
    }

    getOneBoard(no) {
        return axios.get(BOARD_API_BASE_URL + "/" + no);
    }

    updateBoard(no, board) {
        return axios.put(BOARD_API_BASE_URL + "/" + no, board);
    }

    deleteBoard(no) {
        return axios.delete(BOARD_API_BASE_URL + "/" + no);
    }

    login(loginForm) {
        return axios.post(LOGIN_API_BASE_URL, loginForm);
    }
}

export default new BoardService();