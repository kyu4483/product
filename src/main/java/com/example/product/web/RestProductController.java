package com.example.product.web;

import com.example.product.dao.Product;
import com.example.product.svc.ProductSVC;
import com.example.product.web.exception.RestBizException;
import com.example.product.web.rest.SaveRest;
import com.example.product.web.rest.UpdateRest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController //@Controller + @ResponseBody
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class RestProductController {

  private final ProductSVC productSVC;

  //상품등록
  @PostMapping
  public RestResponse<Object> save(@RequestBody SaveRest saveRest){
    RestResponse<Object> res = null;
    log.info("saveRest={}",saveRest);

    //검증

    //등록
    Product product = new Product();
    product.setPname(saveRest.getPname());
    product.setQuantity(saveRest.getQuantity());
    product.setPrice(saveRest.getPrice());

    Long pId = productSVC.save(product);
    product.setPId(pId);

    if(pId > 0 ) {
      res = RestResponse.createRestResponse("00", "성공", product);
    }else{
      res = RestResponse.createRestResponse("99", "실패", "서버오류");
    }
    return res;
  }

  //상품조회
  @GetMapping("/{id}")
  public RestResponse<Object> findById(@PathVariable("id") Long pId){
    RestResponse<Object> res = null;

    //1)상품존재유뮤판단
    if(!productSVC.isExist(pId)){
      throw new RestBizException("99","해당 상품이 없습니다.");
    }

    Optional<Product> findedProduct = productSVC.findById(pId);
    res = RestResponse.createRestResponse("00", "성공", findedProduct);
    return res;
  }

  //상품수정
  @PatchMapping("/{id}")
  public RestResponse<Object> update(
      @PathVariable("id") Long pId,
      @RequestBody UpdateRest updateRest
  ){
    RestResponse<Object> res = null;

    //1)상품존재유뮤판단
    if(!productSVC.isExist(pId)){
      throw new RestBizException("99","해당 상품이 없습니다.");
    }

    //2)수정
    Product product = new Product();
    product.setPname(updateRest.getPname());
    product.setQuantity(updateRest.getQuantity());
    product.setPrice(updateRest.getPrice());

    int updatedRowCnt = productSVC.update(pId, product);
    updateRest.setPId(pId);

    if(updatedRowCnt == 1 ) {
      res = RestResponse.createRestResponse("00", "성공", updateRest);
    }else{
      res = RestResponse.createRestResponse("99", "실패", "서버오류");
    }
    return res;
  }


  //상품삭제
  @DeleteMapping("/{id}")
  public RestResponse<Object> delete(@PathVariable("id") Long pId){
    RestResponse<Object> res = null;

    //1)상품존재유뮤판단
    if(!productSVC.isExist(pId)){
      throw new RestBizException("99","해당 상품이 없습니다.");
    }

    //2)상품삭제
    int deletedRowCnt = productSVC.delete(pId);
    if(deletedRowCnt == 1 ) {
      res = RestResponse.createRestResponse("00", "성공", null);
    }else{
      res = RestResponse.createRestResponse("99", "실패", "서버오류");
    }
    return res;
  }

  //상품목록
  @GetMapping
  public RestResponse<Object> findAll(){
    RestResponse<Object> res = null;
    List<Product> list = productSVC.findAll();
    if(list.size() > 0) {
      res = RestResponse.createRestResponse("00", "성공", list);
    }else{
      res = RestResponse.createRestResponse("01", "상품이 1건도 존재하지 않습니다.", null);
    }
    return res;
  }
}
