'use client'
import Image from 'next/image'
import React from 'react'

export default function Home() {
  const test = localStorage.setItem('test', 'ym')

  console.log('사용' + localStorage.getItem('test'))

  return (
    <main>
      <div className="cdtl_sec_area">
        <div className="cdtl_sec cdtl_detail_num">
          <div className="cdtl_cont_info">
            <div className="cdtl_cont_bx">
              <p className="cdtl_prd_num">상품번호 : 1000035208140</p>
              <p className="cdtl_model_num">모델번호 : MR530SG</p>
            </div>
          </div>
        </div>

        <div className="cdtl_sec cdtl_seller_html ty_1800">
          <h4 className="blind">상품 상세 정보</h4>
          <div className="blind" id="itemNutritionGrid"></div>
          <div className="cdtl_capture_img">
            <iframe
              // frameborder="0"
              id="_ifr_html"
              scrolling="yes"
              src="https://itemdesc.ssg.com/item/iframePItemDtlDesc.ssg?itemId=1000035208140&amp;dispSiteNo=6005&amp;ts=20240312141941/m2x/mixed/main/image/optimize"
              // ="overflow: auto; width: 100%; margin: 0px; padding: 0px; border: none; height: 53358px;"
              title="상세내용"
            ></iframe>
          </div>
          <div className="cdtl_capture_img">
            <div className="cdtl_tmpl_cont ty_grocery"></div>
          </div>
          <div className="cdtl_seller_html_collapse">
            {/* <!-- 활성시 active --> */}
            <button className="btn_collapse ctrl_collapse" type="button">
              <span className="collapse_on">상세정보 펼쳐보기</span>
              <span className="collapse_off">상세정보 접기</span>
            </button>
          </div>
        </div>
        <div className="cdtl_sec">
          <div className="cdtl_sec_titarea ty_tbl">
            <h4 className="cdtl_tit_info">상품필수정보</h4>
          </div>
          <div className="cdtl_cont_info">
            <div className="cdtl_tbl ty2">
              <table summary="상품 필수정보 보여주는 표">
                <caption>상품 필수정보</caption>
                {/* <colgroup><col style="width:22%"/><col/></colgroup> */}
                <tbody>
                  <tr>
                    <th scope="row">
                      <div className="in">제품의 주소재</div>
                    </th>
                    <td>
                      <div className="in">상세설명참조</div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">
                      <div className="in">색상</div>
                    </th>
                    <td>
                      <div className="in">상세설명참조</div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">
                      <div className="in">치수 및 굽높이</div>
                    </th>
                    <td>
                      <div className="in">상세설명참조</div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">
                      <div className="in">취급시 주의사항</div>
                    </th>
                    <td>
                      <div className="in">
                        세탁이 가능한 제품에 한하여 단독세탁하십시오. 세탁가능여부는 상품택을 참조 하십시오.가죽제품의
                        경우는 세탁이 불가합니다.세척시 세탁기에 절대 넣지 마십시오.세제는 중성세제를 사용해주십시오.
                        표백제나 표백성분이 있는 성분은 사용을 하지 마십시오.사용전 주의사항은 제품에 붙은 안내 택또는
                        라벨을 반드시 참고하여 주시기 바랍니다.
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">
                      <div className="in">품질보증기준</div>
                    </th>
                    <td>
                      <div className="in">관련 법 및 소비자분쟁해결 규정에 따름</div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">
                      <div className="in">A/S 책임자 및 전화번호</div>
                    </th>
                    <td>
                      <div className="in">(고객센터) 병행수입(A/S불가)/1661-8756</div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">
                      <div className="in">제조사/수입자</div>
                    </th>
                    <td>
                      <div className="in">뉴발란스/티원글로벌</div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">
                      <div className="in">제조국</div>
                    </th>
                    <td>
                      <div className="in">상세설명참조</div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div className="cdtl_sec cdtl_exchange">
          <div className="cdtl_cont_info">
            <div className="cdtl_tbl">
              <table summary="교환/반품/AS 정보 보여주는 표">
                <caption>교환/반품/AS 정보</caption>
                <colgroup>
                  {/* <col style="width:22%" /> */}
                  <col />
                </colgroup>
                <tbody>
                  <tr>
                    <th scope="row">
                      <div className="in">교환/반품 주소</div>
                    </th>
                    <td>
                      <div className="in">(17342) 경기 이천시 대월면 초지리 57번지</div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </main>
  )
}
