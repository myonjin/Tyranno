import HeaderTitle from '@/components/ui/HeaderTitle'

function Login() {
    return (
        <main>
            <HeaderTitle title="로그인" />
            <div>
                <form>
                    <div>
                        <label>
                            <input type="text" placeholder="아이디" />
                        </label>

                        <label>
                            <input type="text" placeholder="비밀번호" />
                        </label>
                    </div>

                    <div>
                        <span>
                            <input type="checkbox" value="Y"></input>
                            <label>아이디 저장</label>
                        </span>
                    </div>
                    <div>
                        <button type="submit">로그인</button>
                    </div>

                    <div>
                        <a>아이디 찾기</a>
                        <a>비밀번호 찾기</a>
                        <a>회원가입</a>
                    </div>
                </form>

                <ul>
                    <li>
                        <a>
                            <span>
                                <span></span>
                            </span>
                            <span>네이버</span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <span>
                                <span></span>
                            </span>
                            <span>카카오</span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <span>
                                <span></span>
                            </span>
                            <span>애플</span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <span>
                                <span></span>
                            </span>
                            <span>토스</span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <span>
                                <span></span>
                            </span>
                            <span>휴대폰</span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <span>
                                <span></span>
                            </span>
                            <span>네이버</span>
                        </a>
                    </li>
                </ul>

                <div>
                    <span>AD</span>
                    <button type="submit">
                        휴대폰 간편 로그인
                        <span>광고</span>
                    </button>
                </div>

                <a>
                    <span>비회원 조회하기</span>
                </a>
            </div>
        </main>
    )
}
export default Login
