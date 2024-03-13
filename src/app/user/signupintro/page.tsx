import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons'
import React from 'react'
import Image from 'next/image'
import signupcoupon from '@/images/signupcoupon.png'
// /Users/jimin/develop/ssg-frontend/src/images/signupcoupon.png
import './signup.css'

function Signup() {
    return (
        <div>
            <div className="signup_header_menu">
                <FontAwesomeIcon icon={faArrowLeft} style={{ width: '20px', height: '20px' }} />
                <p style={{ fontSize: '20px', textAlign: 'center', flex: 1 }}>회원가입</p>
            </div>
            <h3 style={{ padding: '2%' }}>
                믿고 사는 즐거움 <br />
                SSG.COM에 오신것을 환영합니다.
            </h3>
            <div>
                <Image src={signupcoupon} alt="할인쿠폰안내" width={400} height={300} />
            </div>
            <div>
                <button className="button-groups" style={{ backgroundColor: '#ff5452' }}>
                    멤버십 혜택 받고 가입하기
                </button>
                <button className="button-groups">통합회원만 가입하기</button>
            </div>
        </div>
    )
}

export default Signup
