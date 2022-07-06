package com.example.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: ProtocolMessage.proto")
public final class ProtocolMessageServiceGrpc {

  private ProtocolMessageServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.ProtocolMessageService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.EntryMessage,
      com.example.grpc.ProtocolMessage.ResponseEntryMessage> getNewEntryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "newEntry",
      requestType = com.example.grpc.ProtocolMessage.EntryMessage.class,
      responseType = com.example.grpc.ProtocolMessage.ResponseEntryMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.EntryMessage,
      com.example.grpc.ProtocolMessage.ResponseEntryMessage> getNewEntryMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.EntryMessage, com.example.grpc.ProtocolMessage.ResponseEntryMessage> getNewEntryMethod;
    if ((getNewEntryMethod = ProtocolMessageServiceGrpc.getNewEntryMethod) == null) {
      synchronized (ProtocolMessageServiceGrpc.class) {
        if ((getNewEntryMethod = ProtocolMessageServiceGrpc.getNewEntryMethod) == null) {
          ProtocolMessageServiceGrpc.getNewEntryMethod = getNewEntryMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.ProtocolMessage.EntryMessage, com.example.grpc.ProtocolMessage.ResponseEntryMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "newEntry"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.EntryMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.ResponseEntryMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ProtocolMessageServiceMethodDescriptorSupplier("newEntry"))
              .build();
        }
      }
    }
    return getNewEntryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.PingHello,
      com.example.grpc.ProtocolMessage.Ack> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ping",
      requestType = com.example.grpc.ProtocolMessage.PingHello.class,
      responseType = com.example.grpc.ProtocolMessage.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.PingHello,
      com.example.grpc.ProtocolMessage.Ack> getPingMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.PingHello, com.example.grpc.ProtocolMessage.Ack> getPingMethod;
    if ((getPingMethod = ProtocolMessageServiceGrpc.getPingMethod) == null) {
      synchronized (ProtocolMessageServiceGrpc.class) {
        if ((getPingMethod = ProtocolMessageServiceGrpc.getPingMethod) == null) {
          ProtocolMessageServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.ProtocolMessage.PingHello, com.example.grpc.ProtocolMessage.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.PingHello.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new ProtocolMessageServiceMethodDescriptorSupplier("ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.Election,
      com.example.grpc.ProtocolMessage.Election> getElectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "election",
      requestType = com.example.grpc.ProtocolMessage.Election.class,
      responseType = com.example.grpc.ProtocolMessage.Election.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.Election,
      com.example.grpc.ProtocolMessage.Election> getElectionMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.Election, com.example.grpc.ProtocolMessage.Election> getElectionMethod;
    if ((getElectionMethod = ProtocolMessageServiceGrpc.getElectionMethod) == null) {
      synchronized (ProtocolMessageServiceGrpc.class) {
        if ((getElectionMethod = ProtocolMessageServiceGrpc.getElectionMethod) == null) {
          ProtocolMessageServiceGrpc.getElectionMethod = getElectionMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.ProtocolMessage.Election, com.example.grpc.ProtocolMessage.Election>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "election"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.Election.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.Election.getDefaultInstance()))
              .setSchemaDescriptor(new ProtocolMessageServiceMethodDescriptorSupplier("election"))
              .build();
        }
      }
    }
    return getElectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.Elected,
      com.example.grpc.ProtocolMessage.Elected> getElectedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "elected",
      requestType = com.example.grpc.ProtocolMessage.Elected.class,
      responseType = com.example.grpc.ProtocolMessage.Elected.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.Elected,
      com.example.grpc.ProtocolMessage.Elected> getElectedMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.Elected, com.example.grpc.ProtocolMessage.Elected> getElectedMethod;
    if ((getElectedMethod = ProtocolMessageServiceGrpc.getElectedMethod) == null) {
      synchronized (ProtocolMessageServiceGrpc.class) {
        if ((getElectedMethod = ProtocolMessageServiceGrpc.getElectedMethod) == null) {
          ProtocolMessageServiceGrpc.getElectedMethod = getElectedMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.ProtocolMessage.Elected, com.example.grpc.ProtocolMessage.Elected>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "elected"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.Elected.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.Elected.getDefaultInstance()))
              .setSchemaDescriptor(new ProtocolMessageServiceMethodDescriptorSupplier("elected"))
              .build();
        }
      }
    }
    return getElectedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.Delivery,
      com.example.grpc.ProtocolMessage.Delivery> getMakeDeliveryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "makeDelivery",
      requestType = com.example.grpc.ProtocolMessage.Delivery.class,
      responseType = com.example.grpc.ProtocolMessage.Delivery.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.Delivery,
      com.example.grpc.ProtocolMessage.Delivery> getMakeDeliveryMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.Delivery, com.example.grpc.ProtocolMessage.Delivery> getMakeDeliveryMethod;
    if ((getMakeDeliveryMethod = ProtocolMessageServiceGrpc.getMakeDeliveryMethod) == null) {
      synchronized (ProtocolMessageServiceGrpc.class) {
        if ((getMakeDeliveryMethod = ProtocolMessageServiceGrpc.getMakeDeliveryMethod) == null) {
          ProtocolMessageServiceGrpc.getMakeDeliveryMethod = getMakeDeliveryMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.ProtocolMessage.Delivery, com.example.grpc.ProtocolMessage.Delivery>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "makeDelivery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.Delivery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.Delivery.getDefaultInstance()))
              .setSchemaDescriptor(new ProtocolMessageServiceMethodDescriptorSupplier("makeDelivery"))
              .build();
        }
      }
    }
    return getMakeDeliveryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.StatDelivery,
      com.example.grpc.ProtocolMessage.StatDelivery> getStatDeliveryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "statDelivery",
      requestType = com.example.grpc.ProtocolMessage.StatDelivery.class,
      responseType = com.example.grpc.ProtocolMessage.StatDelivery.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.StatDelivery,
      com.example.grpc.ProtocolMessage.StatDelivery> getStatDeliveryMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.ProtocolMessage.StatDelivery, com.example.grpc.ProtocolMessage.StatDelivery> getStatDeliveryMethod;
    if ((getStatDeliveryMethod = ProtocolMessageServiceGrpc.getStatDeliveryMethod) == null) {
      synchronized (ProtocolMessageServiceGrpc.class) {
        if ((getStatDeliveryMethod = ProtocolMessageServiceGrpc.getStatDeliveryMethod) == null) {
          ProtocolMessageServiceGrpc.getStatDeliveryMethod = getStatDeliveryMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.ProtocolMessage.StatDelivery, com.example.grpc.ProtocolMessage.StatDelivery>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "statDelivery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.StatDelivery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.ProtocolMessage.StatDelivery.getDefaultInstance()))
              .setSchemaDescriptor(new ProtocolMessageServiceMethodDescriptorSupplier("statDelivery"))
              .build();
        }
      }
    }
    return getStatDeliveryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProtocolMessageServiceStub newStub(io.grpc.Channel channel) {
    return new ProtocolMessageServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProtocolMessageServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ProtocolMessageServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProtocolMessageServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ProtocolMessageServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ProtocolMessageServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void newEntry(com.example.grpc.ProtocolMessage.EntryMessage request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.ResponseEntryMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getNewEntryMethod(), responseObserver);
    }

    /**
     */
    public void ping(com.example.grpc.ProtocolMessage.PingHello request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Ack> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    public void election(com.example.grpc.ProtocolMessage.Election request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Election> responseObserver) {
      asyncUnimplementedUnaryCall(getElectionMethod(), responseObserver);
    }

    /**
     */
    public void elected(com.example.grpc.ProtocolMessage.Elected request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Elected> responseObserver) {
      asyncUnimplementedUnaryCall(getElectedMethod(), responseObserver);
    }

    /**
     */
    public void makeDelivery(com.example.grpc.ProtocolMessage.Delivery request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Delivery> responseObserver) {
      asyncUnimplementedUnaryCall(getMakeDeliveryMethod(), responseObserver);
    }

    /**
     */
    public void statDelivery(com.example.grpc.ProtocolMessage.StatDelivery request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.StatDelivery> responseObserver) {
      asyncUnimplementedUnaryCall(getStatDeliveryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getNewEntryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.ProtocolMessage.EntryMessage,
                com.example.grpc.ProtocolMessage.ResponseEntryMessage>(
                  this, METHODID_NEW_ENTRY)))
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.ProtocolMessage.PingHello,
                com.example.grpc.ProtocolMessage.Ack>(
                  this, METHODID_PING)))
          .addMethod(
            getElectionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.ProtocolMessage.Election,
                com.example.grpc.ProtocolMessage.Election>(
                  this, METHODID_ELECTION)))
          .addMethod(
            getElectedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.ProtocolMessage.Elected,
                com.example.grpc.ProtocolMessage.Elected>(
                  this, METHODID_ELECTED)))
          .addMethod(
            getMakeDeliveryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.ProtocolMessage.Delivery,
                com.example.grpc.ProtocolMessage.Delivery>(
                  this, METHODID_MAKE_DELIVERY)))
          .addMethod(
            getStatDeliveryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.ProtocolMessage.StatDelivery,
                com.example.grpc.ProtocolMessage.StatDelivery>(
                  this, METHODID_STAT_DELIVERY)))
          .build();
    }
  }

  /**
   */
  public static final class ProtocolMessageServiceStub extends io.grpc.stub.AbstractStub<ProtocolMessageServiceStub> {
    private ProtocolMessageServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProtocolMessageServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProtocolMessageServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProtocolMessageServiceStub(channel, callOptions);
    }

    /**
     */
    public void newEntry(com.example.grpc.ProtocolMessage.EntryMessage request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.ResponseEntryMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNewEntryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ping(com.example.grpc.ProtocolMessage.PingHello request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Ack> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void election(com.example.grpc.ProtocolMessage.Election request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Election> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void elected(com.example.grpc.ProtocolMessage.Elected request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Elected> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getElectedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void makeDelivery(com.example.grpc.ProtocolMessage.Delivery request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Delivery> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMakeDeliveryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void statDelivery(com.example.grpc.ProtocolMessage.StatDelivery request,
        io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.StatDelivery> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStatDeliveryMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ProtocolMessageServiceBlockingStub extends io.grpc.stub.AbstractStub<ProtocolMessageServiceBlockingStub> {
    private ProtocolMessageServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProtocolMessageServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProtocolMessageServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProtocolMessageServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.ProtocolMessage.ResponseEntryMessage newEntry(com.example.grpc.ProtocolMessage.EntryMessage request) {
      return blockingUnaryCall(
          getChannel(), getNewEntryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.ProtocolMessage.Ack ping(com.example.grpc.ProtocolMessage.PingHello request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.ProtocolMessage.Election election(com.example.grpc.ProtocolMessage.Election request) {
      return blockingUnaryCall(
          getChannel(), getElectionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.ProtocolMessage.Elected elected(com.example.grpc.ProtocolMessage.Elected request) {
      return blockingUnaryCall(
          getChannel(), getElectedMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.ProtocolMessage.Delivery makeDelivery(com.example.grpc.ProtocolMessage.Delivery request) {
      return blockingUnaryCall(
          getChannel(), getMakeDeliveryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.ProtocolMessage.StatDelivery statDelivery(com.example.grpc.ProtocolMessage.StatDelivery request) {
      return blockingUnaryCall(
          getChannel(), getStatDeliveryMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ProtocolMessageServiceFutureStub extends io.grpc.stub.AbstractStub<ProtocolMessageServiceFutureStub> {
    private ProtocolMessageServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProtocolMessageServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProtocolMessageServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProtocolMessageServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.ProtocolMessage.ResponseEntryMessage> newEntry(
        com.example.grpc.ProtocolMessage.EntryMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getNewEntryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.ProtocolMessage.Ack> ping(
        com.example.grpc.ProtocolMessage.PingHello request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.ProtocolMessage.Election> election(
        com.example.grpc.ProtocolMessage.Election request) {
      return futureUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.ProtocolMessage.Elected> elected(
        com.example.grpc.ProtocolMessage.Elected request) {
      return futureUnaryCall(
          getChannel().newCall(getElectedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.ProtocolMessage.Delivery> makeDelivery(
        com.example.grpc.ProtocolMessage.Delivery request) {
      return futureUnaryCall(
          getChannel().newCall(getMakeDeliveryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.ProtocolMessage.StatDelivery> statDelivery(
        com.example.grpc.ProtocolMessage.StatDelivery request) {
      return futureUnaryCall(
          getChannel().newCall(getStatDeliveryMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_NEW_ENTRY = 0;
  private static final int METHODID_PING = 1;
  private static final int METHODID_ELECTION = 2;
  private static final int METHODID_ELECTED = 3;
  private static final int METHODID_MAKE_DELIVERY = 4;
  private static final int METHODID_STAT_DELIVERY = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProtocolMessageServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProtocolMessageServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NEW_ENTRY:
          serviceImpl.newEntry((com.example.grpc.ProtocolMessage.EntryMessage) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.ResponseEntryMessage>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((com.example.grpc.ProtocolMessage.PingHello) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Ack>) responseObserver);
          break;
        case METHODID_ELECTION:
          serviceImpl.election((com.example.grpc.ProtocolMessage.Election) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Election>) responseObserver);
          break;
        case METHODID_ELECTED:
          serviceImpl.elected((com.example.grpc.ProtocolMessage.Elected) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Elected>) responseObserver);
          break;
        case METHODID_MAKE_DELIVERY:
          serviceImpl.makeDelivery((com.example.grpc.ProtocolMessage.Delivery) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.Delivery>) responseObserver);
          break;
        case METHODID_STAT_DELIVERY:
          serviceImpl.statDelivery((com.example.grpc.ProtocolMessage.StatDelivery) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.ProtocolMessage.StatDelivery>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ProtocolMessageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProtocolMessageServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.ProtocolMessage.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProtocolMessageService");
    }
  }

  private static final class ProtocolMessageServiceFileDescriptorSupplier
      extends ProtocolMessageServiceBaseDescriptorSupplier {
    ProtocolMessageServiceFileDescriptorSupplier() {}
  }

  private static final class ProtocolMessageServiceMethodDescriptorSupplier
      extends ProtocolMessageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProtocolMessageServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProtocolMessageServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProtocolMessageServiceFileDescriptorSupplier())
              .addMethod(getNewEntryMethod())
              .addMethod(getPingMethod())
              .addMethod(getElectionMethod())
              .addMethod(getElectedMethod())
              .addMethod(getMakeDeliveryMethod())
              .addMethod(getStatDeliveryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
