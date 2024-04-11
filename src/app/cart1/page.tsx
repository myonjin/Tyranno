import Image from 'next/image'
function CartOption() {
    return (
        <div>
            <div
                className=" bg-white  rounded-t-xl min-h-[200px]"
                style={{ boxShadow: '0px -4px 10px 0px rgba(0, 0, 0, 0.1)' }}
            >
                <div className=" w-full h-5 p-4 flex items-center justify-center mb-2  border-b-2">
                    <span className="font-bold">옵션변경</span>
                    <Image
                        width={20}
                        height={20}
                        src="https://img.icons8.com/ios-glyphs/30/back.png"
                        alt="back"
                        style={{ transform: 'rotate(270deg)' }}
                    />
                </div>
                <div className="p-4">
                    <div className=" font-bold border-b-2 border-black">옵션변경</div>
                </div>
            </div>
            <button className="flex justify-center items-center bg-red-500  h-12 w-full">
                <span className="  text-white">변경하기</span>
            </button>
        </div>
    )
}
export default CartOption
