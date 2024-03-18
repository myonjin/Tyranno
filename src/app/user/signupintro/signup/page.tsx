'use client'
import HeaderTitle from '@/components/ui/HeaderTitle'
import './signup.css'
import { useEffect } from 'react'
import PostcodeButton from '@/components/common/address'
function signup() {
    return (
        <div>
            <HeaderTitle title="신세계포인트 통합회원 가입" />
            <div style={{ backgroundColor: '#f8f8f8', padding: '15px 20px' }}>
                <h3>회원 정보</h3>
            </div>
            <form className="flex-col">
                <div className="box">
                    <dl className="cmem_ip">
                        <dt>
                            <span className="cmem_require">
                                <span className="star" aria-hidden="true" style={{ color: 'red' }}>
                                    *
                                </span>
                                <label>이름</label>
                            </span>
                        </dt>
                    </dl>
                    <input type="text" className=" mt-1 p-2 ml-7 h-10 w-full  border text-sm" />
                    <button
                        className=" mt-1 ml-2 h-10 w-28 text-sm  "
                        style={{ backgroundColor: '#f0f0f0', fontWeight: 'bold' }}
                    >
                        중복확인
                    </button>
                </div>
                <div className="box">
                    <dl className="cmem_ip">
                        <dt>
                            <span className="cmem_require">
                                <span className="star" aria-hidden="true" style={{ color: 'red' }}>
                                    *
                                </span>
                                <label>비밀번호</label>
                            </span>
                        </dt>
                    </dl>
                    <div className="flex-col w-full ">
                        <input
                            type="text"
                            className=" mt-1 p-2 ml-30 h-10 w-full mb-2 border text-sm "
                            placeholder="영문, 숫자 조합 8~20자리"
                        />

                        <input
                            type="text"
                            className=" mt-1 p-2 ml-30 h-10 w-full  border text-sm "
                            placeholder="비밀번호 재확인"
                        />
                    </div>
                </div>
                <div className="box">
                    <dl className="flex">
                        <dt>
                            <span className="star" aria-hidden="true" style={{ color: 'red' }}>
                                *
                            </span>
                            <label>이름</label>
                        </dt>
                        <span className=" mt-1 p-2 ml-6 h-10 w-full mb-1 text-sm ">이지민</span>
                    </dl>
                </div>
                <div className="box">
                    <dl className="cmem_ip">
                        <dt>
                            <span className="cmem_require">
                                <span className="star" aria-hidden="true" style={{ color: 'red' }}>
                                    *
                                </span>
                                <label>주소</label>
                            </span>
                        </dt>
                    </dl>
                    <input type="text" className=" mt-1 p-2 ml-7 h-10 w-full  border text-sm" />
                    <PostcodeButton />
                    {/* <button
            type="button"
            onClick={handleClick}
            className=" mt-1 ml-2 h-10 w-28 text-sm bg-stone-500 text-white "
            style={{
                backgroundColor: '#666666',
                color: 'white',
                fontWeight: 'bold',
            }}
        >
            우편번호
        </button> */}
                </div>
                <div className="box">
                    <dl className="flex">
                        <dt>
                            <span className="star" aria-hidden="true" style={{ color: 'red' }}>
                                *
                            </span>
                            <label>휴대폰번호</label>
                        </dt>
                        <span className=" mt-1 p-2 ml-6 h-10 w-full mb-1 text-sm ">010-6877-4842</span>
                    </dl>
                </div>
                <div className="box">
                    <dl className="cmem_ip">
                        <dt>
                            <span className="cmem_require">
                                <span className="star" aria-hidden="true" style={{ color: 'red' }}>
                                    *
                                </span>
                                <label>이메일주소</label>
                            </span>
                        </dt>
                    </dl>

                    <input
                        type="text"
                        className=" mt-1 p-2 ml-30 h-10 w-full mb-1 border text-sm "
                        placeholder="이메일 주소"
                    />
                </div>
                <button type="submit">Sign Up</button>
            </form>
        </div>
    )
}

export default signup
