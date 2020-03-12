package org.anonymous.swagger.controller;

import io.swagger.annotations.*;
import org.anonymous.swagger.model.Film;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2019/11/7 22:47
 */
@Api(value = "FilmController", tags = {"电影访问接口"})
@RestController
@RequestMapping("/film")
public class FilmController {

    @ApiOperation("添加一部电影")
    @PostMapping("/addFilm")
    @ApiResponses({
            @ApiResponse(code = 1000, message = "成功"),
            @ApiResponse(code = 1001, message = "失败"),
            @ApiResponse(code = 1002, response = Film.class, message = "缺少参数")
    })
    public String addFilm(@ApiParam("电影名称") @RequestParam("filmName") String filmName,
                          @ApiParam(value = "分数", allowEmptyValue = true, example = "1") @RequestParam("score") Short score,
                          @ApiParam("发布时间") @RequestParam(value = "publishTime", required = false) String publishTime,
                          @ApiParam(value = "创建者 id", example = "1") @RequestParam("creatorId") Long creatorId) {

        return null;
    }

    @GetMapping("getFilms")
    @ApiOperation("根据名字获取电影")
    @ApiResponses({
            @ApiResponse(code = 1000, message = "成功"),
            @ApiResponse(code = 1001, message = "失败"),
            @ApiResponse(code = 1002, message = "缺少参数")
    })
    public String getFilmByName(@ApiParam("电影名字") @RequestParam("filmName") String filmName) {
        return null;
    }

    @PostMapping("/delFilm")
    @ApiOperation("根据电影名删除电影")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filmName", value = "电影名", dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "id", value = "电影 id", dataTypeClass = int.class, paramType = "query", example = "1")
    })
    public String deleteFilmByNameOrId(HttpServletRequest request) {
        String filmName = request.getParameter("filmName");
        return null;
    }

}
