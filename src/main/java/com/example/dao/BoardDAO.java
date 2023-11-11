package com.example.dao;

import com.example.bean.BoardVO;
import com.example.util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    private final String BOARD_INSERT="insert into BOARD(title, writer, content) values(?,?,?))";
    private final String BOARD_UPDATE="update BOARD set title=?, writer=?, content=? where seq=?";
    private final String BOARD_DELETE="delete from Board where seq=?";
    private final String BOARD_GET="select * from BOARD where seq=?";
    private final String Board_LIST="select * from BOARD order by seq desc";

    /*public int insertBoard(BoardVO vo);
    public void deleteBoard(BoardVO vo);
    public int updateBoard(BoardVO vo);
    public BoardVO getBoard(int seq);
    public List<BoardVO> getBoardList();*/

    public int insertBoard(BoardVO vo) {
        System.out.println("===> JDBC로 insertBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getWriter());
            stmt.setString(3, vo.getContent());
            stmt.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public BoardVO getBoard(int seq){
        BoardVO one = new BoardVO();
        System.out.println("===>JDB로 getBoard() 기능 처리");
        try{
            conn= JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setInt(1, seq);
            rs=stmt.executeQuery();
            if(rs.next()){
                one.setSeq(rs.getInt("seq"));
                one.setCategory(rs.getString("category"));
                one.setTitle(rs.getString("title"));
                one.setWriter(rs.getString("writer"));
                one.setContent(rs.getString("content"));
                one.setCnt(rs.getInt("cnt"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return one;
    }

    public int updateBoard(BoardVO vo){
        System.out.println("===>JDBC로 updateBoard() 기능 처리");
        try{
            conn= JDBCUtil.getConnection();
            stmt=conn.prepareStatement(BOARD_UPDATE);
            stmt.setString(1, vo.getCategory());
            stmt.setString(2, vo.getTitle());
            stmt.setString(3, vo.getWriter());
            stmt.setString(4, vo.getContent());
            stmt.setInt(vo.getSeq(), 5);

            System.out.println(vo.getCategory()+"-"+vo.getTitle()+"-"+vo.getWriter()+"-"+vo.getContent()+"-"+vo.getSeq());
            stmt.executeUpdate();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteBoard(BoardVO vo){
        System.out.println("===>JDBC로 deleteBoard() 기능 처리");
        try{
            conn=JDBCUtil.getConnection();
            stmt=conn.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getSeq());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BoardVO> getBoardList(){
        List<BoardVO> list=new ArrayList<BoardVO>();
        System.out.println("===>JDBC로 getBoard() 기능 처리");
        try {
            conn=JDBCUtil.getConnection();
            stmt=conn.prepareStatement(Board_LIST);
            rs=stmt.executeQuery();
            while(rs.next()){
                BoardVO one = new BoardVO();
                one.setSeq(rs.getInt("seq"));
                one.setTitle(rs.getString("title"));
                one.setWriter(rs.getString("writer"));
                one.setContent(rs.getString("content"));
                one.setRegdate(rs.getDate("regdate"));
                one.setCnt(rs.getInt("cnt"));
                list.add(one);
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
