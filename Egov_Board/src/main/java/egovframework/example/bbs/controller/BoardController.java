package egovframework.example.bbs.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/*import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;*/
import egovframework.example.bbs.service.BoardService;
import egovframework.example.bbs.model.BoardVO;
import egovframework.example.bbs.model.DefaultVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {
    
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "boardService")
    private BoardService boardService;

    @RequestMapping(value = "/mainList.do")
    public String list(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
        boardVO.setPageUnit(propertiesService.getInt("pageUnit"));
        boardVO.setPageSize(propertiesService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
        paginationInfo.setPageSize(boardVO.getPageSize());

        boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
        boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> list = boardService.selectBoardList(boardVO);
        model.addAttribute("resultList", list);

        int totCnt = boardService.selectBoardListTotCnt(boardVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/board/mainList";
    }
    
    // 등록/수정/조회 화면 호출
    @RequestMapping(value = "/mgmt.do", method = RequestMethod.GET)
    public String mgmt(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request) throws Exception {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        System.out.println("Today" + strToday);
        
        boardVO = boardService.selectBoard(boardVO);
        
        if (request.getSession().getAttribute("userId") != null) {
            boardVO.setWriter(request.getSession().getAttribute("userId").toString());
        }
        if (request.getSession().getAttribute("userName") != null) {        
            boardVO.setWriterNm(request.getSession().getAttribute("userName").toString());
        }
        
        model.addAttribute("boardVO", boardVO);
        
        return "/board/mgmt";
    }

    // 등록 기능
    @RequestMapping(value = "/insertBoard.do", method = RequestMethod.POST)
    public String insertBoard(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
        boardService.insertBoard(boardVO);
        return "redirect:/mainList.do";
    }

    // 수정 기능
    @RequestMapping(value = "/updateBoard.do", method = RequestMethod.POST)
    public String updateBoard(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
        boardService.updateBoard(boardVO);
        return "redirect:/mainList.do";
    }

    // 삭제 기능
    @RequestMapping(value = "/deleteBoard.do", method = RequestMethod.POST)
    public String deleteBoard(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
        boardService.deleteBoard(boardVO);
        return "redirect:/mainList.do";
    }
    
    @RequestMapping(value = "/view.do")
    public String view(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        System.out.println("Today" + strToday);
        
        boardService.updateBoardCount(boardVO);
        
        boardVO = boardService.selectBoard(boardVO);
        
        model.addAttribute("boardVO", boardVO);
        model.addAttribute("strToday", strToday); 
        
        List<?> list = boardService.selectReplyList(boardVO);
        model.addAttribute("resultList", list);
        
        return "/board/view";
    }
    
    // 등록 수행
    @RequestMapping(value = "/reply.do", method=RequestMethod.POST)
    public String reply(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
        
        boardService.insertReply(boardVO);
        return "redirect:/view.do?idx="+boardVO.getIdx();
    }
    
    @RequestMapping(value = "/login.do")
    public String login(@RequestParam("user_id") String user_id,
            @RequestParam("password") String password,
            ModelMap model, HttpServletRequest request) throws Exception {
        // HttpServletRequest request
        // String aa = request.getParameter("user_id");
        System.out.println("userid:"+user_id);
        System.out.println("password:"+password);
        
        BoardVO boardVO = new BoardVO();
        boardVO.setUserId(user_id);
        boardVO.setPassword(password);
        String user_name = boardService.selectLoginCheck(boardVO);
        
        if(user_name != null && !"".equals(user_name) ) {
            request.getSession().setAttribute("userId", user_id);
            request.getSession().setAttribute("userName", user_name);
        }else {
            request.getSession().setAttribute("userId", "");
            request.getSession().setAttribute("userName", "");
            model.addAttribute("msg","사용자 정보가 올바르지 않습니다.");
        }

        return "redirect:mainList.do";
    }
    
    @RequestMapping(value = "/logout.do")
    public String logout(ModelMap model,HttpServletRequest request) throws Exception {
        request.getSession().invalidate();
        return "redirect:mainList.do";
    }
}

