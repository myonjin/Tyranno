package com.tyranno.ssg.cart.application;

import com.tyranno.ssg.cart.domain.Cart;
import com.tyranno.ssg.cart.dto.Request.CartAddDto;
import com.tyranno.ssg.cart.dto.Request.CartCountModifyDto;
import com.tyranno.ssg.cart.dto.Response.CartListDto;
import com.tyranno.ssg.cart.infrastructure.CartRepository;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.infrastructure.OptionRepository;
import com.tyranno.ssg.product.infrastructure.DiscountRepository;
import com.tyranno.ssg.product.infrastructure.ProductThumRepository;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import com.tyranno.ssg.vendor.infrastructure.VendorProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private final UsersRepository usersRepository;
    private final CartRepository cartRepository;
    private final OptionRepository optionRepository;
    private final VendorProductRepository vendorProductRepository;
    private final ProductThumRepository productThumRepository;
    private final DiscountRepository discountRepository;

    @Transactional
    @Override
    public void addCart(CartAddDto cartAddDto, String uuid) {
        Users users = usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        Option option = optionRepository.findById(cartAddDto.getOptionId())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OPTION));

        // 이미 추가할려는 옵션이 장바구니에 존재
        if (cartRepository.existsByOption(option)) {
            throw new GlobalException(ResponseStatus.ALREADY_EXIST_OPTION);
        }
        // 이미 추가할려는 상품이 장바구니에 존재
        if (cartRepository.existsByOptionProduct(option.getProduct())) {
            throw new GlobalException(ResponseStatus.ALREADY_EXIST_PRODUCT);
        }
        cartRepository.save(cartAddDto.toEntity(users, option));
    }

    @Override
    public List<CartListDto> getCartList(String uuid) {
        List<Cart> carts = cartRepository.findByUsersUuid(uuid);

        return carts.stream().map(cart -> {
            Long productId = cart.getOption().getProduct().getId();
            String imageUrl = productThumRepository.findByProductId(productId)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCTTHUM))
                    .getImageUrl();
            int discount = discountRepository.findById(productId)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DISCOUNT))
                    .getDiscount();
            String vendorName = vendorProductRepository.findByProductId(productId)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_VENDORPRODUCT))
                    .getVendor().getVendorName();

            return CartListDto.FromEntity(cart, imageUrl, discount, vendorName);
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteCartList(List<Long> cartDeleteList) {

        cartRepository.deleteByIdIn(cartDeleteList);
    }

    @Transactional
    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Transactional
    @Override
    public void modifyItemCount(CartCountModifyDto cartCountModifyDto) {
        Cart cart = cartRepository.findById(cartCountModifyDto.getCartId())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_CART));

        cartRepository.save(cartCountModifyDto.toEntity(cart));
    }
}
